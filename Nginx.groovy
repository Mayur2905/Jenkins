// Host a website using Nginx with Jenkins Pipeline

pipeline {
    agent any
    stages {
        stage('Installing Nginx') {
            steps {
                sh ''' sudo apt-get update'''
                sh ''' sudo apt-get install nginx -y'''
                sh ''' sudo systemctl start nginx'''
                }
            }
        stage('Downloding Tepmplate') {
            steps {
                sh ''' sudo wget https://www.free-css.com/assets/files/free-css-templates/download/page294/digian.zip'''
                sh ''' sudo apt-get install unzip'''
                sh ''' sudo unzip digian.zip'''
                sh ''' sudo mv ./digian-html /var/www/html'''
            }
        }
    }
}
