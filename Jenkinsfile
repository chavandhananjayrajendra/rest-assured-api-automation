
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Java and Maven') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Run API Tests') {
            steps {
                bat 'mvn --batch-mode clean test'
            }
        }
    }

    post {
        always {
            junit testResults: '**/target/surefire-reports/*.xml',
                  allowEmptyResults: true
        }
        success {
            echo 'API tests completed successfully.'
        }
        failure {
            echo 'API tests failed. Check the console output.'
        }
    }
}
