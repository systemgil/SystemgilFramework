pipeline {
    agent any

   environment { 
       MAVEN_HOME = '"C:/Program Files/apache-maven-3.9.9"' 
       PATH = "${env.PATH}:${env.MAVEN_HOME}/bin"
       
   }

    stages { 
        stage('Checkout') {
            steps { 
                // Clonar el repositorio desde GitHub desde la rama main 
                git branch: 'main', url: 'https://github.com/systemgil/SystemgilFramework.git' 
                
            } 
        }
    
        stage('Build') {
            steps {
                // Compilar el proyecto (ejecuta Maven)
                script {
                    sh '"${env.MAVEN_HOME}\\bin\\mvn" clean install'
                }
            }
        }

        stage('Run Tests') {
            steps {
                // Ejecutar las pruebas de TestNG con Maven
                script {
                    sh "${MAVEN_HOME}/bin/mvn test"
                }
            }
        }

        stage('Post-Test Actions') {
            steps {
                // Acciones después de ejecutar las pruebas, como limpiar o notificar
                echo 'Pruebas ejecutadas correctamente'
            }
        }
    }

    post {
        always {
            // Limpiar recursos, por ejemplo, destruir contenedores o cerrar sesión si es necesario
            echo 'Pipeline completado'
        }
    }
}
