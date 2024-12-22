pipeline {
    agent any
    environment {
        // �����������CredentialsID �� Jenkins, � �� ������ �����
        GIT_CREDENTIALS_ID = 'github-credentials'
        GIT_REPO_URL = 'https://github.com/Greeeeeezly/TestJenkins.git'
        BRANCH_NAME = 'master'
    }
    stages {
        stage('Clone Repository') {
            steps {
                // ����������� credentials ����� Jenkins
                git branch: env.BRANCH_NAME,
                    credentialsId: env.GIT_CREDENTIALS_ID,
                    url: env.GIT_REPO_URL
            }
        }
        stage('Prepare docker-compose') {
            steps {
                script {
                    // ������������� docker-compose, ���� �� ��� �� ����������
                    if (!fileExists('/usr/local/bin/docker-compose')) {
                        sh '''
                            curl -L "https://github.com/docker/compose/releases/download/v2.23.3/docker-compose-$(uname -s)-$(uname -m)" -o $HOME/docker-compose
                            chmod +x $HOME/docker-compose
                            export PATH=$PATH:$HOME
                        '''
                    } else {
                        echo "docker-compose ��� ����������"
                    }
                }
            }
        }
        stage('Build') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean install'
            }
        }
        stage('Deploy') {
            steps {
                script {
                    // ������������� ���������� � ��������� �� ������ � ���������� --build
                    sh '''
                        docker-compose -f docker-compose.yml down || true
                        docker-compose -f docker-compose.yml up --build -d
                    '''
                }
            }
        }
    }
}
