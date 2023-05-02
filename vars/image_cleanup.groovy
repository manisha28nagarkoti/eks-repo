def call(String image_name,String tag){
sh 'echo removing $image_name and $image_name:$tag'
  sh 'docker rmi $image_name $image_name:$tag'

}
