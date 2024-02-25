pipeline{
    agent{
        label 'phpapp'
    
    }
    stages{
        stage('Pull a file from git')
        {
            steps{
                sh 'git clone https://github.com/AaravRajSIngh/Chatbot.git'
            }
        }
        stage('Installing Apache2')
        {
            steps{
                sh " sudo apt-get update"
                sh " sudo apt-get install apache2 -y"
                sh " sudo  systemctl start apache2"
            }
        }
        stage('Install PHP')
        {
            steps{
                sh " sudo  apt-get install php -y"
                sh " sudo  apt install php-all-dev -y"
                sh " sudo apt install php-intl -y"
                sh " sudo apt install php-mbstring -y"
            }
        }
        stage('Install MySQL')
        {
            steps{
                sh " sudo apt-get install mysql-server -y"
                sh " sudo systemctl start mysql"
            }
        }
        stage('Deploying sysPass')
        {
            steps{
                sh " sudo cp -r ./Chatbot/* /var/www/html"
            }
        }
    }
}