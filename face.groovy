pipeline{
    agent{
        label 'phpapp'
        }
    stages
    {
        stage('Pull a file from git')
        {
            steps{
                sh 'git clone https://github.com/chandrikadeb7/Face-Mask-Detection.git'
            }
        }
        stage('Installing file from requirements.txt')
        {
            steps{
                sh "sudo apt-get update"
                sh "sudo apt-get install python3 -y"
                sh "sudo apt-get install python3-pip -y"
                sh "sudo pip3 install -r ./Face-Mask-Detection/requirements.txt"
            }
        }
        stage("Running the code"){
            steps{
                sh "python3 train_mask_detector.py --dataset dataset "
                sh "python3 detect_mask_image.py --image examples/example_01.png"
                sh "python3 detect_mask_video.py"
            }
        }
        stage('Running the app.py file')
        {
            steps{
                sh "streamlit run app.py "
            }
        }
    }
}