def call(){
      sh '`aws sts assume-role --role-arn arn:aws:iam::388606509852:role/Shared-Jenkins-Role --role-session-name nitin > op`'
      env.AWS_SECRET_ACCESS_KEY = sh(script:'cat op | grep SecretAccessKey | awk \'{print $2}\' | sed -s \'s/"//g; s/,//g\'', returnStdout: true).trim()
      env.AWS_ACCESS_KEY_ID = sh(script:'cat op | grep AccessKeyId | awk \'{print $2}\' | sed -s \'s/"//g; s/,//g\'', returnStdout: true).trim()
      env.AWS_SESSION_TOKEN = sh(script:'cat op | grep SessionToken | awk \'{print $2}\' | sed -s \'s/"//g; s/,//g\'', returnStdout: true).trim()

}
