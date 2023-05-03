def call(String ecr_repo,String repo_name,String tag){
  def image_name = $ecr_repo/$repo_name
sh 'echo removing $image_name and $image_name:$tag'
  sh 'docker rmi $image_name $image_name:$tag'

}
