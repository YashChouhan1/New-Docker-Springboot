pipeline {
    agent any
    
    parameters {
        string(name: 'DOCKER_HUB_USER', defaultValue: '', description: 'Insert docker hub username')
        string(name: 'DOCKER_HUB_PASSWORD', defaultValue: '', description: 'Insert docker hub password')
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Clone the Git repository's master branch
                    def gitRepoUrl = 'https://github.com/YashChouhan1/New-Docker-Springboot.git'

                    checkout([$class: 'GitSCM', 
                        branches: [[name: '*/master']], 
                        userRemoteConfigs: [[url: gitRepoUrl]], 
                        extensions: [[$class: 'CleanBeforeCheckout'], [$class: 'CloneOption', noTags: false, shallow: true, depth: 1]]
                    ])
                }
            }
        }

        stage('Build') {
            steps {
                // Build your application here (e.g., compile, package, etc.)
                sh '''
                ls
                echo "In Build Stage"
                '''
            }
        }

        
        stage('Deploy') {
            steps {
                // Deploy your application to a target environment (e.g., staging, production)
                sh 'echo "In Deploy Stage"'

                sh "docker login -u ${params.DOCKER_HUB_USER} -p ${params.DOCKER_HUB_PASSWORD}"                  
                
                echo "Build number: $BUILD_NUMBER"
                echo "3"
                echo "creating backend image"
                sh 'docker build -f ./Dockerfile -t yashchouhan/backend-v1:$BUILD_NUMBER .'
                echo "4"
                echo "creating frontend image"
                sh 'docker build -f ./Dockerfile-frontend -t yashchouhan/frontend-v1:$BUILD_NUMBER .'

                echo "current images -"
                sh 'docker images'

                echo "pushing backend image"
                sh 'docker push yashchouhan/backend-v1:$BUILD_NUMBER'

                echo "pushing frontend image"
                sh 'docker push yashchouhan/frontend-v1:$BUILD_NUMBER'
            }
        }   
    }

    post {
        success {
            // Actions to perform when the pipeline succeeds
            echo 'Pipeline succeeded!'
        }
        failure {
            // Actions to perform when the pipeline fails
            echo 'Pipeline failed!'
        }
    }
}