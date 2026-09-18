pipeline {
    agent any

    options {
        timeout(time: 60, unit: 'MINUTES')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh './gradlew build -x test'
            }
        }

        stage('Test') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    sh './gradlew test'
                }
            }
        }

        stage('Generate .env') {
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
                        echo "EUREKA_DEFAULT_ZONE=http://localhost:8761/eureka/" >> .env
                        echo "TOSS_PAYMENTS_BASE_URL=https://api.tosspayments.com" >> .env
                        echo "TOSS_PAYMENTS_CLIENT_KEY=${TOSS_PAYMENTS_CLIENT_KEY}" >> .env
                        echo "TOSS_PAYMENTS_SECRET_KEY=${TOSS_PAYMENTS_SECRET_KEY}" >> .env
                        echo "TOSS_PAYMENTS_SUCCESS_URL=http://13.125.101.82:8080/payments/toss/success" >> .env
                        echo "TOSS_PAYMENTS_FAIL_URL=http://13.125.101.82:8080/payments/toss/fail" >> .env
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
                        echo "EMAIL_VERIFICATION_FRONTEND_URL=http://13.125.101.82:8080/email-verification" >> .env
                        echo "KAKAO_CLIENT_ID=${KAKAO_CLIENT_ID}" >> .env
                        echo "KAKAO_CLIENT_SECRET=${KAKAO_CLIENT_SECRET}" >> .env
                        echo "AWS_ACCESS_KEY=${AWS_ACCESS_KEY}" >> .env
                        echo "AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY}" >> .env
                        echo "AWS_SECRET_KEY=${AWS_SECRET_KEY}" >> .env
                        echo "AWS_SECRET_ACCESS_KEY=${AWS_SECRET_KEY}" >> .env
                        echo "AWS_S3_BUCKET=${AWS_S3_BUCKET}" >> .env
                        echo "SWEET_TRACKER_API_KEY=${SWEET_TRACKER_API_KEY}" >> .env
                        echo "SWEET_TRACKER_API_BASE_URL=https://info.sweettracker.co.kr" >> .env
                    '''
                }
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker compose down || true'
                sh 'docker compose build'
                sh 'docker compose up -d'
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
