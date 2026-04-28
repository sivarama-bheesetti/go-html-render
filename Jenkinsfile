pipeline {
    agent any

    tools {
        go 'go-1.19'
    }

    environment {
        GOTMPDIR = "${env.JENKINS_HOME}/go-cache"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/USERNAME/go-html-render.git',
                    credentialsId: 'github-pat'
            }
        }

        stage('Build') {
            steps {
                dir('src') {
                    sh '''
                        mkdir -p "$GOTMPDIR"
                        go build -o generator main.go
                    '''
                }
            }
        }

        stage('Generate HTML') {
            steps {
                dir('src') {
                    sh './generator'
                }
            }
        }
    }

    post {
        success {
            publishHTML([
                reportDir: 'src',
                reportFiles: 'index.html',
                reportName: 'Dynamic HTML Generator'
            ])
        }
    }
}
