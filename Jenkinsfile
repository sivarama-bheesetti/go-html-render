pipeline {
    agent any

    tools {
        go 'go-1.19'
    }

    environment {
        COMMIT = "${env.GIT_COMMIT}"
        BRANCH = "${env.GIT_BRANCH}"
        GOTMPDIR = "${env.JENKINS_HOME}/go-cache"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Prepare') {
            steps {
                sh '''
                    mkdir -p "$GOTMPDIR"
                    go version
                '''
            }
        }

        stage('Build') {
            steps {
                dir('src') {
                    sh '''
                        go build -o generator main.go
                    '''
                }
            }
        }

        stage('Test') {
            steps {
                dir('src') {
                    sh '''
                        go fmt *.go
                    '''
                }
            }
        }

        stage('Generate HTML') {
            steps {
                dir('src') {
                    sh '''
                        ./generator
                    '''
                }
            }
        }
    }

    post {
        success {
            publishHTML([
                reportDir: 'src',
                reportFiles: 'index.html',
                reportName: 'Dynamic HTML Generator',
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: false
            ])
        }

        failure {
            echo 'Build failed!'
        }
    }
}

