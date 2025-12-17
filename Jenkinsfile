pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
         maven 'M2_HOME'

    }

    environment {
        SONAR_TOKEN= credentials('sonar-token')
        DOCKER_IMAGE = "mohamedaliromdhane/student-management"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'mohamedali-romdhane',
                    url: 'https://github.com/mohamedalirom/devops.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }


        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubeServer') {
                    sh '''
                        mvn sonar:sonar \
                        -Dsonar.projectKey=student-management \
                        -Dsonar.host.url=http://localhost:9000
                    '''
                }
            }
        }


        stage('Quality Gate') {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE:latest .'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credss',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                        echo "Logging to Docker Hub as $DOCKER_USER"
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin

                        docker tag student-management:latest dalirom123/student-management:latest
                        docker push dalirom123/student-management:latest
                    '''
                }
            }
        }

    }
}
