def call(String devops_git_url,String service_name){
 env.new_docker_image=docker_repo+":"+env.tag
  service = split

        sh """

          rm -rf *
          
          git clone -b "$devops_branch_name" "$devops_git_url"
          cd devops
          sed -E -i 's/($service_name:).*/$service_name:$tag/' 'service/values.yaml'
          git add *
          git commit -m "adding"
          git push origin kubernetes    
        """


}
