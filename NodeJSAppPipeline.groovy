// Hosting NodeJS Application using Jenkins Pipeline

pipeline{
    agent {
        label 'agent1'
    }
    stages{
        stage('Pull a file from git')
        {
            steps{
                git 'https://github.com/Mayur2905/TicTacToe.git'
            }
        }
        stage('Building the app')
        {
            steps{
                sh 'sudo apt install node -y'
                sh 'sudo apt install npm -y'
                sh 'npm install'
            }
        }
        stage('Running the app')
        {
            steps{
                sh 'npm run start&'
            }
        }
    }
}