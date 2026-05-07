pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "harshraj04/test-automation-1:latest"
        PATH = "/usr/local/bin:/opt/homebrew/bin:/usr/bin:/bin:/usr/sbin:/sbin"
    }

    stages {

        stage('Pull Docker Image') {
            steps {
                script {
                    echo "========== Pulling Docker image: ${DOCKER_IMAGE} =========="
                    sh "docker pull ${DOCKER_IMAGE}"
                }
            }
        }

        stage('Run Tests in Docker') {
            steps {
                script {
                    echo '========== Running tests in Docker container =========='
                    sh '/bin/bash -c "docker run --rm -v ${WORKSPACE}:/app -w /app ${DOCKER_IMAGE} mvn clean test"'
                }
            }
        }

        stage('Publish Results') {
            steps {
                script {
                    echo '========== Publishing test results =========='
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }

    post {
        always {
            echo '========== Pipeline finished =========='
        }
        success {
            echo '✅ All tests passed!'
        }
        failure {
            echo '❌ Build failed!'
        }
    }
}

