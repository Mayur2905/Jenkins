// Creating a maven project in Jenkins using pipeline and deploying it on AWS S3 bucket.

pipeline {
    agent {
        label 'maven'
    }
    stages {
        stage('Install Maven') {
            steps {
                sh 'sudo apt-get update'
                sh 'sudo apt-get install maven -y'
            }
        }
        stage('Isntall AWS CLI') {
            steps {
                sh 'sudo apt-get install awscli -y'
            }
        }
        stage('Pull a file from git') {
            steps {
                git 'https://github.com/AnupDudhe/studentapp-ui.git'
            }   
        }   
        stage('Build the code') {
            steps {
                sh 'mvn clean package'
            }
        } 
        stage('Creating a S3 bucket') {
            steps {
                sh 'aws s3api create-bucket --bucket  studentappbucket775604 --region us-east-2 --create-bucket-configuration LocationConstraint=us-east-2'
                //sh 'aws s3api create-bucket --bucket StudentApp-Bucket --region us-east-2 --create-bucket-configuration LocationConstraint=us-east-2 --versioning-configuration Status=Enabled'
            }
        }
        stage('Uploading the war file to S3') {
            steps {
                sh 'aws s3 cp target/*.war s3://studentappbucket775604'
            }
        }

    }
}

