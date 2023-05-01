 call(docker_git_url,service-name){

    env.new_docker_image=docker_repo+":"+env.tag

        sh """

          rm -rf *   
          
          git clone -b "${param.branch_name}" "${docker_git_url}"
          cd devops
          sed -E -i 's/${service-name:}-service.*/${service-name}-service:$tag/' '${service-name}/values.yaml'    
          git add *
          git commit -m "adding"
          git push origin kubernetes    
        """
    }
 