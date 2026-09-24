pipeline {
    agent any

    options {
        timeout(time: 60, unit: 'MINUTES')
    }

    environment {
        APP_SERVICES = 'product cart member order payment settlement notification gateway auction ai db-migration'
        COMMON_MODULES = 'common-security common-monitoring common-messaging'

        AWS_REGION    = 'ap-northeast-2'
        ECR_REGISTRY  = '800728769281.dkr.ecr.ap-northeast-2.amazonaws.com'
        ECR_NAMESPACE = 'goods-commerce'
        APP_SERVER    = '3.34.151.217'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Detect Changes') {
            steps {
                script {
                    def appServices = env.APP_SERVICES.split(' ') as List
                    def commonModules = env.COMMON_MODULES.split(' ') as List

                    def changed = null
                    if (env.GIT_PREVIOUS_SUCCESSFUL_COMMIT) {
                        def baseOk = sh(script: 'git cat-file -e "$GIT_PREVIOUS_SUCCESSFUL_COMMIT^{commit}"', returnStatus: true) == 0
                        if (baseOk) {
                            def diff = sh(script: 'git diff --name-only "$GIT_PREVIOUS_SUCCESSFUL_COMMIT" HEAD', returnStdout: true).trim()
                            changed = diff ? diff.split('\n') as List : []
                        }
                    }

                    def buildAll = (changed == null)
                    def composeChanged = false
                    def elasticsearch = false
                    def services = []
                    def testModules = []

                    if (!buildAll) {
                        for (f in changed) {
                            def top = f.contains('/') ? f.split('/')[0] : ''
                            if (f in ['build.gradle', 'settings.gradle', 'gradle.properties', 'gradlew', 'gradlew.bat'] || f.startsWith('gradle/')) {
                                buildAll = true
                            } else if (f == 'docker-compose.yml') {
                                composeChanged = true
                            } else if (f.startsWith('product/docker/elasticsearch/')) {
                                elasticsearch = true
                            } else if (top in appServices) {
                                services << top
                            } else if (top in commonModules) {
                                testModules << top
                                appServices.each { svc ->
                                    if (readFile("${svc}/build.gradle").contains("project(':${top}')")) {
                                        services << svc
                                    }
                                }
                            }
                        }
                    }

                    if (buildAll) {
                        services = appServices
                        elasticsearch = true
                    }
                    services = services.unique()
                    testModules = (testModules + services).unique()

                    env.BUILD_ALL        = buildAll.toString()
                    env.COMPOSE_CHANGED  = composeChanged.toString()
                    env.TEST_MODULES     = testModules.join(' ')
                    env.DEPLOY_SERVICES  = (services + (elasticsearch ? ['elasticsearch'] : [])).join(' ')
                    env.NEED_DEPLOY      = (buildAll || composeChanged || env.DEPLOY_SERVICES.trim()) ? 'true' : 'false'

                    echo "buildAll=${env.BUILD_ALL}, composeChanged=${env.COMPOSE_CHANGED}"
                    echo "test modules: ${env.TEST_MODULES ?: '(none)'}"
                    echo "deploy services: ${env.DEPLOY_SERVICES ?: '(none)'}"
                    echo "changed files: ${changed == null ? '(no base commit, full build)' : changed}"
                }
            }
        }

        stage('Test') {
            when { expression { env.TEST_MODULES?.trim() } }
            steps {
                script {
                    if (env.BUILD_ALL == 'true') {
                        sh './gradlew test'
                    } else {
                        def tasks = env.TEST_MODULES.split(' ').collect { ":${it}:test" }.join(' ')
                        sh "./gradlew ${tasks}"
                    }
                }
            }
        }

        stage('Generate .env') {
            when { expression { env.NEED_DEPLOY == 'true' } }
            steps {
                withCredentials([
                    string(credentialsId: 'DB_NAME', variable: 'DB_NAME'),
                    string(credentialsId: 'DB_USER_NAME', variable: 'DB_USER_NAME'),
                    string(credentialsId: 'DB_USER_PASSWORD', variable: 'DB_USER_PASSWORD'),
                    string(credentialsId: 'DB_URL', variable: 'DB_URL'),
                    string(credentialsId: 'JWT_SECRET_KEY', variable: 'JWT_SECRET_KEY'),
                    string(credentialsId: 'ACCOUNT_VERIFICATION_SECRET_KEY', variable: 'ACCOUNT_VERIFICATION_SECRET_KEY'),
                    string(credentialsId: 'PAYMENT_WITHDRAW_CRYPTO_SECRET_KEY', variable: 'PAYMENT_WITHDRAW_CRYPTO_SECRET_KEY'),
                    string(credentialsId: 'TOSS_PAYMENTS_CLIENT_KEY', variable: 'TOSS_PAYMENTS_CLIENT_KEY'),
                    string(credentialsId: 'TOSS_PAYMENTS_SECRET_KEY', variable: 'TOSS_PAYMENTS_SECRET_KEY'),
                    string(credentialsId: 'OPENAI_API_KEY', variable: 'OPENAI_API_KEY'),
                    string(credentialsId: 'SMTP_USERNAME', variable: 'SMTP_USERNAME'),
                    string(credentialsId: 'SMTP_PASSWORD', variable: 'SMTP_PASSWORD'),
                    string(credentialsId: 'KAKAO_CLIENT_ID', variable: 'KAKAO_CLIENT_ID'),
                    string(credentialsId: 'KAKAO_CLIENT_SECRET', variable: 'KAKAO_CLIENT_SECRET'),
                    string(credentialsId: 'AWS_ACCESS_KEY', variable: 'AWS_ACCESS_KEY'),
                    string(credentialsId: 'AWS_SECRET_KEY', variable: 'AWS_SECRET_KEY'),
                    string(credentialsId: 'AWS_S3_BUCKET', variable: 'AWS_S3_BUCKET'),
                    string(credentialsId: 'SWEET_TRACKER_API_KEY', variable: 'SWEET_TRACKER_API_KEY'),
                    string(credentialsId: 'EC2_HOST', variable: 'EC2_HOST'),
                ]) {
                    sh '''
                        rm -f .env
                        echo "POSTGRES_DB=${DB_NAME}" >> .env
                        echo "POSTGRES_USER=${DB_USER_NAME}" >> .env
                        echo "POSTGRES_PASSWORD=${DB_USER_PASSWORD}" >> .env
                        echo "DB_NAME=${DB_NAME}" >> .env
                        echo "DB_URL=${DB_URL}" >> .env
                        echo "DB_USER_NAME=${DB_USER_NAME}" >> .env
                        echo "DB_USER_PASSWORD=${DB_USER_PASSWORD}" >> .env
                        echo "SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092" >> .env
                        echo "REDIS_HOST=redis" >> .env
                        echo "REDIS_PORT=6379" >> .env
                        echo "MEMBER_SIGNED_UP_TOPIC=member-signed-up" >> .env
                        echo "JWT_SECRET_KEY=${JWT_SECRET_KEY}" >> .env
                        echo "ACCOUNT_VERIFICATION_SECRET_KEY=${ACCOUNT_VERIFICATION_SECRET_KEY}" >> .env
                        echo "PAYMENT_WITHDRAW_CRYPTO_SECRET_KEY=${PAYMENT_WITHDRAW_CRYPTO_SECRET_KEY}" >> .env
                        echo "TOSS_PAYMENTS_BASE_URL=https://api.tosspayments.com" >> .env
                        echo "TOSS_PAYMENTS_CLIENT_KEY=${TOSS_PAYMENTS_CLIENT_KEY}" >> .env
                        echo "TOSS_PAYMENTS_SECRET_KEY=${TOSS_PAYMENTS_SECRET_KEY}" >> .env
                        echo "TOSS_PAYMENTS_SUCCESS_URL=http://${EC2_HOST}:8080/payments/toss/success" >> .env
                        echo "TOSS_PAYMENTS_FAIL_URL=http://${EC2_HOST}:8080/payments/toss/fail" >> .env
                        echo "TOSS_PAYMENTS_WIDGET_ENABLED=true" >> .env
                        echo "OPENAI_API_KEY=${OPENAI_API_KEY}" >> .env
                        echo "AI_EMBEDDING_MODEL=text-embedding-3-small" >> .env
                        echo "PROJECT_OPENAI_BASE_URL=https://api.openai.com" >> .env
                        echo "AI_RECOMMENDATION_RERANK_MODEL=gpt-5.4-nano" >> .env
                        echo "AI_PRODUCT_DRAFT_ASSIST_MODEL=gpt-5.4-nano" >> .env
                        echo "AI_AUCTION_PRICE_RECOMMENDATION_MODEL=gpt-5.4-nano" >> .env
                        echo "EMAIL_PROVIDER=smtp" >> .env
                        echo "SMTP_HOST=smtp.gmail.com" >> .env
                        echo "SMTP_PORT=587" >> .env
                        echo "SMTP_USERNAME=${SMTP_USERNAME}" >> .env
                        echo "SMTP_PASSWORD=${SMTP_PASSWORD}" >> .env
                        echo "SMTP_AUTH=true" >> .env
                        echo "SMTP_STARTTLS_ENABLE=true" >> .env
                        echo "MAIL_FROM=no-reply@todaylunch.local" >> .env
                        echo "MAIL_FROM_NAME=TodayLunch" >> .env
                        echo "EMAIL_VERIFICATION_FRONTEND_URL=http://${EC2_HOST}:8080/email-verification" >> .env
                        echo "KAKAO_CLIENT_ID=${KAKAO_CLIENT_ID}" >> .env
                        echo "KAKAO_CLIENT_SECRET=${KAKAO_CLIENT_SECRET}" >> .env
                        echo "AWS_ACCESS_KEY=${AWS_ACCESS_KEY}" >> .env
                        echo "AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY}" >> .env
                        echo "AWS_SECRET_KEY=${AWS_SECRET_KEY}" >> .env
                        echo "AWS_SECRET_ACCESS_KEY=${AWS_SECRET_KEY}" >> .env
                        echo "AWS_S3_BUCKET=${AWS_S3_BUCKET}" >> .env
                        echo "SWEET_TRACKER_API_KEY=${SWEET_TRACKER_API_KEY}" >> .env
                        echo "SWEET_TRACKER_API_BASE_URL=https://info.sweettracker.co.kr" >> .env
                        echo "ECR_REGISTRY=${ECR_REGISTRY}" >> .env
                    '''
                }
            }
        }

        stage('Build & Push') {
            when { expression { env.NEED_DEPLOY == 'true' } }
            steps {
                script {
                    sh "aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_REGISTRY}"

                    def appServiceList = env.APP_SERVICES.split(' ') as List
                    def imageTag = env.GIT_COMMIT.take(8)

                    def deployList = env.BUILD_ALL == 'true'
                        ? appServiceList
                        : env.DEPLOY_SERVICES.split(' ').findAll { it in appServiceList }

                    deployList.each { svc ->
                        def ecrImage = "${ECR_REGISTRY}/${ECR_NAMESPACE}/${svc}"
                        sh "docker compose build ${svc}"
                        sh "docker tag ${ecrImage}:latest ${ecrImage}:${imageTag}"
                        sh "docker push ${ecrImage}:${imageTag}"
                        sh "docker push ${ecrImage}:latest"
                        sh "docker image rm ${ecrImage}:${imageTag}"
                        sh "docker image rm ${ecrImage}:latest"
                    }

                }
            }
        }

        stage('Deploy') {
            when { expression { env.NEED_DEPLOY == 'true' } }
            steps {
                script {
                    sshagent(['app-server-key']) {
                        // EC2-B로 최신 파일 전송
                        sh "scp -o StrictHostKeyChecking=no .env ubuntu@${APP_SERVER}:~/app/.env"
                        sh "scp -o StrictHostKeyChecking=no docker-compose.yml ubuntu@${APP_SERVER}:~/app/docker-compose.yml"
                        sh "scp -r -o StrictHostKeyChecking=no monitoring ubuntu@${APP_SERVER}:~/app/monitoring"
                        sh """
                            ssh -o StrictHostKeyChecking=no ubuntu@${APP_SERVER} '
                                mkdir -p ~/app/db-migration/src/main/resources/db/seed
                            '
                        """
                        sh "scp -o StrictHostKeyChecking=no db-migration/src/main/resources/db/seed/dev_seed_payment_settlement.sql ubuntu@${APP_SERVER}:~/app/db-migration/src/main/resources/db/seed/"

                        // elasticsearch는 EC2-B에서 직접 빌드 (변경 시에만)
                        if (env.DEPLOY_SERVICES?.contains('elasticsearch')) {
                            sh """
                                ssh -o StrictHostKeyChecking=no ubuntu@${APP_SERVER} '
                                    mkdir -p ~/app/elasticsearch
                                '
                            """
                            sh "scp -o StrictHostKeyChecking=no product/docker/elasticsearch/Dockerfile ubuntu@${APP_SERVER}:~/app/elasticsearch/Dockerfile"
                            sh """
                                ssh -o StrictHostKeyChecking=no ubuntu@${APP_SERVER} '
                                    docker build -t goods-commerce/elasticsearch:latest ~/app/elasticsearch/
                                '
                            """
                        }

                        def pullCmd = env.BUILD_ALL == 'true'
                            ? 'docker compose pull --ignore-pull-failures'
                            : "docker compose pull --ignore-pull-failures ${env.DEPLOY_SERVICES}"

                        def upCmd = env.BUILD_ALL == 'true'
                            ? 'docker compose up -d --no-build'
                            : "docker compose up -d --no-build --no-deps ${env.DEPLOY_SERVICES}"

                        sh """
                            ssh -o StrictHostKeyChecking=no ubuntu@${APP_SERVER} '
                                aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_REGISTRY} &&
                                cd ~/app &&
                                ${pullCmd} &&
                                ${upCmd} &&
                                docker image prune -f
                            '
                        """
                    }
                }
            }
        }
    }

    post {
        success {
            echo '배포 완료'
            step([$class: 'GitHubCommitStatusSetter', statusResultSource: [$class: 'ConditionalStatusResultSource', results: [[$class: 'AnyBuildResult', message: 'Build succeeded', state: 'SUCCESS']]]])
        }
        failure {
            echo '빌드 또는 배포 실패'
            step([$class: 'GitHubCommitStatusSetter', statusResultSource: [$class: 'ConditionalStatusResultSource', results: [[$class: 'AnyBuildResult', message: 'Build failed', state: 'FAILURE']]]])
        }
        unstable {
            step([$class: 'GitHubCommitStatusSetter', statusResultSource: [$class: 'ConditionalStatusResultSource', results: [[$class: 'AnyBuildResult', message: 'Tests failed', state: 'FAILURE']]]])
        }
    }
}
