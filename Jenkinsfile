pipeline {
    agent any

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
                sh './gradlew test'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -f gateway/Dockerfile -t ${IMAGE_NAME}:${BUILD_NUMBER} ."
                sh "docker tag ${IMAGE_NAME}:${BUILD_NUMBER} ${IMAGE_NAME}:latest"
            }
        }

        stage('Deploy') {
            steps {
                sh "docker stop ${CONTAINER_NAME} || true"
                sh "docker rm ${CONTAINER_NAME} || true"
                sh "docker run -d --name ${CONTAINER_NAME} -p ${HOST_PORT}:8080 ${IMAGE_NAME}:latest"
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
