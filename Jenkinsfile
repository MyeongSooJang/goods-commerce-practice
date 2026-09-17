pipeline {
    agent any

    options {
        timeout(time: 30, unit: 'MINUTES')
    }

    environment {
        IMAGE_NAME = "gateway-app"
        CONTAINER_NAME = "gateway-jenkins"
        HOST_PORT = "18080"
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

        stage('Docker Build') {
            steps {
                sh "docker build -f gateway/Dockerfile -t ${IMAGE_NAME}:${BUILD_NUMBER} ."
                sh "docker tag ${IMAGE_NAME}:${BUILD_NUMBER} ${IMAGE_NAME}:latest"
            }
        }

        stage('Approve') {
            steps {
                input message: '운영 배포할까요?', ok: '배포'
            }
        }

        stage('Deploy') {
            steps {
                withCredentials([string(credentialsId: 'JWT_SECRET_KEY', variable: 'JWT_SECRET_KEY')]) {
                    sh "docker stop ${CONTAINER_NAME} || true"
                    sh "docker rm ${CONTAINER_NAME} || true"
                    sh "docker run -d --name ${CONTAINER_NAME} -p ${HOST_PORT}:8080 -e JWT_SECRET_KEY=${JWT_SECRET_KEY} ${IMAGE_NAME}:latest"
                }
            }
        }
    }

    post {
        success {
            echo "배포 완료 - http://localhost:${HOST_PORT} 에서 확인 가능"
        }
        failure {
            echo '빌드 또는 배포 실패'
        }
    }
}
