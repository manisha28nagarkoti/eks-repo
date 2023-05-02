def call(String repo_url){ 
withCredentials([sshUserPrivateKey(credentialsId: env.credential_git, keyFileVariable: 'SSH_KEY')]) {
                    sh """
                        eval `ssh-agent`
                        ssh-add $SSH_KEY
                        ssh-keyscan github.com >> ~/.ssh/known_hosts
                        git clone $repo_url .
                    """
                }
  
}
