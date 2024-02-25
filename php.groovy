pipeline{
    agent{
        label 'maven'
    }
    // delolying php application using jenkins
    stages{
        stage('Pull a file from git')
        {
            steps{
                git 'https://github.com/Mayur2905/devops.git'
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
        stage('Moving the code to the apache2 folder')
        {
            steps{
                sh " sudo cp -r ./devops/* /var/www/html"
            }
        }
    }
}