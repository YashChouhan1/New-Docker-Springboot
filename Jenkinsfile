pipeline {
    agent any
    
    environment {
      VERSION = '0.1.0'
      RELEASE_VERSION = 'R.2'
    }

    stages {
      stage('Audit tools') {
        steps {
          sh '''
              git version
              java -version
              mvn -version
          '''
        }
      }
      
      stage('Build') {
        steps {
          echo "Building version: ${VERSION} with suffix: ${RELEASE_VERSION}"
          echo 'Mention your Application Build Code here!!!'
          sh 'mvn -f ./pom.xml clean package'
        }
      }
    }
}
