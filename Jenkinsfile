node('docker') {
  stage('Checkout') {
    checkout scm
  }

  stage('Test') {
    sauce('saucelabs') {
      sauceconnect(useGeneratedTunnelIdentifier: true, verboseLogging: true) {
        withEnv(['HOME=$WORKSPACE']) {
          docker.image('vlatombe/maven-make:latest').inside {
            sh 'make'
          }
        }
      }
    }
  }
  stage('Collect Results') {
    step([$class: 'SauceOnDemandTestPublisher'])
  }
}