pipelineJob('DSL_Demo') {

pipeline {
  agent {
     node { 
        label 'BLRCSLFBECM0013'
        } 
  }
  stages {
    stage('Checkout Scm') {
      steps {
        git(branch: '+refs/heads/develop:refs/remotes/origin/develop', credentialsId: 'SSH_cmreub', url: 'ssh://git@blrvslalm0006:7999/es/partyrepos.git')
      }
    }

    stage('No Converter-0') {
      steps {
        echo 'No converter for Builder: hudson.tasks.Ant'
      }
    }

  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  parameters {
    string(name: 'version', defaultValue: '2020.X', description: '')
    string(name: 'jdk.home', defaultValue: '/usr', description: '')
  }
  triggers {
    pollSCM('* * * * * ')
  }
}
}
