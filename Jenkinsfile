pipeline {
    agent any

    tools {
        // Usamos Java 1.8 y Maven 3.9.9 configurados en Jenkins
        jdk 'Java_1.8'   // Asegúrate de configurar este JDK en Jenkins
        maven 'Maven_3.9.9' // Asegúrate de configurar esta versión en Jenkins
    }

    environment {
        MAVEN_HOME = 'C:/Program Files/apache-maven-3.9.9'
        PATH = "${env.PATH}:${env.MAVEN_HOME}/bin"
    }

    stages {
        stage('Checkout') {
            steps {
                // Clonar el repositorio de GitHub
                git branch: 'main', url: 'https://github.com/systemgil/SystemgilFramework.git'
            }
        }

        stage('Build') {
            steps {
                // Compilar el proyecto
                script {
                    // Usar comillas dobles para manejar espacios en la ruta
                    sh "\"${env.MAVEN_HOME}/bin/mvn\" clean compile"
                }
            }
        }

        stage('Run Tests') {
            steps {
                // Ejecutar las pruebas con Maven y TestNG
                script {
                    // Usar comillas dobles para manejar espacios en la ruta
                    sh "\"${env.MAVEN_HOME}/bin/mvn\" test"
                }
            }
        }

        stage('Generate Reports') {
            steps {
                // Generar reportes de Surefire para TestNG
                script {
                    // Usar comillas dobles para manejar espacios en la ruta
                    sh "\"${env.MAVEN_HOME}/bin/mvn\" surefire-report:report"
                }
            }
        }
    }

    post {
        always {
            // Archivar los reportes generados
            archiveArtifacts artifacts: 'target/surefire-reports/*.xml', allowEmptyArchive: true
            junit 'target/surefire-reports/*.xml'
        }
    }
}
