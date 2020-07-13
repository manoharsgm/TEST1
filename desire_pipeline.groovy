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

    stage('Build') {
      steps {
        echo 'No converter for Builder: hudson.tasks.Ant'
		withAnt(installation: 'Version 1.10.3') {
                        sh "ant -f MisysPD/compile.xml"
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
