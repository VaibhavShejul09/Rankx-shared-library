def call(String serviceName){
    stage('Upadte the Helm Values'){
            steps{
                withCredentials([string(credentialsId: 'git-token', variable: 'GIT_TOKEN')]) {
               sh '''
               # Clone the repo
                git clone https://github.com/VaibhavShejul09/rankx-environments.git
                cd rankx-environments

                # Configure Git author
                git config user.email "ci@jenkins.com"
                git config user.name "jenkins"

                # Set remote URL with token safely
                git remote set-url origin https://VaibhavShejul09:$GIT_TOKEN@github.com/VaibhavShejul09/rankx-environments.git

                # IMPORTANT: Pull latest
                git pull origin main

                cd $ENVIRONMENT/${serviceName}

                # Update values.yaml
                sed -i "s/tag:.*/tag: \\"$IMAGE_TAG\\"/" values.yaml

                # Commit and push
                git add values.yaml
                git commit -m "Update image tag to $IMAGE_TAG"
                git push origin main
                '''    
                } 
            }
        }
}