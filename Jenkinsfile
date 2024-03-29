pipeline {
    agent any
    
    environment {
        GIT_CREDS = credentials('Github')
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    sh "echo 'In checkout stage'"

                    // Clone the Git repository's master branch
                    def gitRepoUrl = 'https://github.com/YashChouhan1/New-Docker-Springboot.git'

                    checkout([$class: 'GitSCM', 
                        branches: [[name: '*/new-master']], 
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
		'''
                echo "In Build Stage"

		echo "Build number: $BUILD_NUMBER"

		echo "creating backend image"
		sh 'docker build -f ./Dockerfile -t yashchouhan/backend-nv1:$BUILD_NUMBER .'

		echo "creating frontend image"
		sh 'docker build -f ./Dockerfile-frontend -t yashchouhan/frontend-nv1:$BUILD_NUMBER .'	
            }
        }

        
        stage('Deploy') {
            steps {
		withCredentials([usernamePassword(credentialsId: 'DockerHub', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    script {
                        def loginStatus = sh(script: "docker login -u $USER -p $PASS", returnStatus: true)
                        if (loginStatus == 0) {					        
				sh "echo 'In deploy stage'"
	
				echo 'Docker login succeeded!'
				
				echo "current images -"
				sh 'docker images'
	
				echo "pushing backend image"
				sh 'docker push yashchouhan/backend-nv1:$BUILD_NUMBER'
	
				echo "pushing frontend image"
				sh 'docker push yashchouhan/frontend-nv1:$BUILD_NUMBER'
                        } else {						
			        sh "echo 'In deploy stage'"							
	                        error 'Docker login failed!'
                        }
                    }
                }
            }
        } 
        
        stage('Release'){
            steps {
                // Update docker-compose.yml with new image tags
                sh "echo 'In release stage'" 
                
                sh "sed -i 's/yashchouhan\\/backend-nv1:[^ ]*/yashchouhan\\/backend-nv1:$BUILD_NUMBER/' docker-compose.yml"
                sh "sed -i 's/yashchouhan\\/frontend-nv1:[^ ]*/yashchouhan\\/frontend-nv1:$BUILD_NUMBER/' docker-compose.yml"

                sh 'docker-compose -f docker-compose.yml up -d'
            }
        }
        
        stage('Commit and Push') {
            steps {
                script {

                    sh "echo 'In Commit and Push stage'"
                    
                    sh 'git config --global user.email "yashchouhan2610@gmail.com"'
                    sh 'git config --global user.name "Yash Chouhan"'
                    // Add all changes to the index
                    sh "git add docker-compose.yml"
                    
                    // Commit the changes with a message
                    sh "git commit -m 'auto-commit latest image pushed via ci/cd'"

                    // Push the changes to the remote repository
                    sh "git push https://$GIT_CREDS@github.com/YashChouhan1/New-Docker-Springboot.git HEAD:new-master"
                }
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
