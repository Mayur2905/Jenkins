// Hosting A Php Application using Jenkins Pipeline

pipeline{
    agent{
        label 'agent1'
    
    }
    stages{
        stage('Pull a file from git')
        {
            steps{
                git 'https://github.com/jonastaedcke/prjct.git'
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
        stage('removing file from apache2 folder')
        {
            steps{
                sh " sudo rm -r /var/www/html/*"
            }
        }
        stage('Deploying sysPass')
        {
            steps{
                sh " sudo cp -r ./* /var/www/html"
            }
        }
    }
}