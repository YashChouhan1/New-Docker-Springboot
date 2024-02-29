pipeline {
    agent any

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
            environment {
                DOCKER_HUB_USER = credentials('DOCKER_HUB_USER_ID')
                DOCKER_HUB_PASSWORD = credentials('DOCKER_HUB_PASSWORD_ID')
            }
            steps {
                script {
                    echo "In Deploy Stage"
                    
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_HUB_USER, DOCKER_HUB_PASSWORD) {
                        echo "Build number: $BUILD_NUMBER"

                        echo "creating backend image"
                        docker build -f ./Dockerfile -t yashchouhan/backend:$BUILD_NUMBER .

                        echo "creating frontend image"
                        docker build -f ./Dockerfile-frontend -t yashchouhan/frontend:$BUILD_NUMBER .

                        echo "current images -"
                        docker images

                        echo "pushing backend image"
                        docker push yashchouhan/backend:$BUILD_NUMBER

                        echo "pushing frontend image"
                        docker push yashchouhan/frontend:$BUILD_NUMBER
                    }
                }
            }
        }

        
        // stage('Deploy') {
        //     steps {
        //         // Deploy your application to a target environment (e.g., staging, production)
        //         sh '''
        //             echo "In Deploy Stage"

        //             docker login -u $DOCKER_HUB_USER -p $DOCKER_HUB_PASSWORD                       
        //             echo "Build number: $BUILD_NUMBER"

        //             echo "creating backend image"
        //             docker build -f ./Dockerfile -t yashchouhan/backend:$BUILD_NUMBER .

        //             echo "creating frontend image"
        //             docker build -f ./Dockerfile-frontend -t yashchouhan/frontend:$BUILD_NUMBER .

        //             echo "current images -"
        //             docker images

        //             echo "pushing backend image"
        //             docker push yashchouhan/backend:$BUILD_NUMBER

        //             echo "creating frontend image"
        //             docker push yashchouhan/frontend:$BUILD_NUMBER
        //         '''
        //     }
        // }
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