pipeline{
    agent {
        label 'agent1'
    }
    stages{
        // Install maven
        stage('Install Maven')
        {
            steps{
                sh "  sudo  apt-get update"
                sh "  sudo  apt-get install maven -y"
            }
        }
        stage('Pull a file from git')
        {
            steps{
                git 'https://github.com/AnupDudhe/studentapp-ui'
            }
        }
        stage('Build the code')
        {
            steps{
                sh 'mvn clean package'
            }
        }
        
        stage('Installing Apache2')
        {
            steps{
                sh "sudo apt-get update"
                sh "  sudo  apt-get install apache2 -y"
                sh "  sudo  systemctl start apache2"
                
            }
        }
        stage('Downloading Tomcat 8')
        {
            steps{
                sh 'sudo wget https://dlcdn.apache.org/tomcat/tomcat-8/v8.5.99/bin/apache-tomcat-8.5.99.tar.gz'
                sh 'sudo tar -xvf apache-tomcat-8.5.99.tar.gz'
            }
        }
        // moveing the war file to the tomcat webapps folder
        stage('Deploying war file')
        {
            steps{
                sh "  sudo  mv target/*.war apache-tomcat-8.5.99/webapps/student.war"
            }
        }
        stage('Starting Apache 8')
        {
            steps{
                sh 'sudo ./apache-tomcat-8.5.99/bin/startup.sh'
            }
        }
    }
}


// Groovy pipeline for Gradle

pipeline{
    agent{
        label 'agent1'
    }

    
    stages{
        stage("Install Gradle")
        {
            steps{
                sh "sudo apt-get update"
                sh "sudo apt-get install gradle -y"
            }
        }

        stage('Pull a file from git')
        {
            steps{
                git 'https://github.com/Mayur2905/studentapp-ui.git'
            }
        }
        stage('Build the code')
        {
            steps{
                sh 'gradle clean'
                sh 'gradle build'
            }
        }
        stage('Testing the code')
        {
            steps{
                sh 'gradle test'
            }
        }
        stage('Downloading Tomcat 8')
        {
            steps{
                sh 'sudo wget https://dlcdn.apache.org/tomcat/tomcat-8/v8.5.99/bin/apache-tomcat-8.5.99.tar.gz'
                sh 'sudo tar -xvf apache-tomcat-8.5.99.tar.gz'
            }
        }
        // moveing the war file to the tomcat webapps folder
        stage('Deploying war file')
        {
            steps{
                sh "  sudo  mv build/libs/*.war apache-tomcat-8.5.99/webapps/student.war"
            }
        }
        stage('Starting Apache 8')
        {
            steps{
                sh 'sudo ./apache-tomcat-8.5.99/bin/startup.sh'
            }
        }
    }   
}