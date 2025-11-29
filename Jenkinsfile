pipeline {
    agent any

    tools {
        jdk 'jdk21'
        maven 'maven3'
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token')
        NEXUS = credentials('nexus-auth')
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/mohamedalirom/devops.git'
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean install -DskipTests=false"
            }
        }

        stage('Test') {
            steps {
                sh "mvn test"
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubeServer') {
                    sh """
                        mvn sonar:sonar \
                          -Dsonar.projectKey=student-management \
                          -Dsonar.host.url=http://sonarqube:9000 \
                          -Dsonar.login=$SONAR_TOKEN
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                sh "mvn deploy"
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t student-management:latest ."
            }
        }

        stage('Docker Compose Deploy') {
            steps {
                sh "docker compose down || true"
                sh "docker compose up -d --build"
            }
        }
    }
}
