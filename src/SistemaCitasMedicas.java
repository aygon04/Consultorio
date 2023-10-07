import java.io.*;
import java.util.Scanner;
//Develop.2
public class SistemaCitasMedicas {
//Clases
    public static class Cita {

    private String identificador;
    private String fecha;
    private String hora;
    private String motivo;
    private String doctor;
    private String paciente;

    public Cita(String identificador, String fecha, String hora, String motivo, String doctor, String paciente) {
        this.identificador = identificador;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "identificador='" + identificador + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora=" + hora +
                ", motivo='" + motivo + '\'' +
                ", doctor=" + doctor +
                ", paciente=" + paciente +
                '}';
    }
}
    public static class Doctor {

        private String identificador;
        private String nombreCompleto;
        private String especialidad;

        public Doctor(String identificador, String nombreCompleto, String especialidad) {
            this.identificador = identificador;
            this.nombreCompleto = nombreCompleto;
            this.especialidad = especialidad;
        }

        public String getIdentificador() {
            return identificador;
        }

        public void setIdentificador(String identificador) {
            this.identificador = identificador;
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public void setNombreCompleto(String nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
        }

        public String getEspecialidad() {
            return especialidad;
        }

        public void setEspecialidad(String especialidad) {
            this.especialidad = especialidad;
        }

        @Override
        public String toString() {
            return "Doctor{" +
                    "identificador='" + identificador + '\'' +
                    ", nombreCompleto='" + nombreCompleto + '\'' +
                    ", especialidad='" + especialidad + '\'' +
                    '}';
        }
    }
    public static class Paciente {

        private String identificador;
        private String nombreCompleto;

        public Paciente(String identificador, String nombreCompleto) {
            this.identificador = identificador;
            this.nombreCompleto = nombreCompleto;
        }

        public String getIdentificador() {
            return identificador;
        }

        public void setIdentificador(String identificador) {
            this.identificador = identificador;
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public void setNombreCompleto(String nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
        }

        @Override
        public String toString() {
            return "Paciente{" +
                    "identificador='" + identificador + '\'' +
                    ", nombreCompleto='" + nombreCompleto + '\'' +
                    '}';
        }
    }

    //Endsclases


    private static final String ARCHIVO_CSV = "citas.csv";
    private static final String ARCHIVO_CSV_Citas = "citas2.csv";
    private static final String ARCHIVO_CSV_Doctor = "Doctor.csv";
    private static final String ARCHIVO_CSV_Paciente = "Paciente.csv";

    public static void main(String[] args) {
        // Iniciar sesión
        String nombreUsuario, contraseña;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario: ");
        nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        contraseña = scanner.nextLine();

        boolean autenticado = validarCredenciales(nombreUsuario, contraseña);

        // Si el usuario está autenticado, mostrar el menú principal
        if (autenticado) {
            mostrarMenuPrincipal();
        } else {
            System.out.println("El usuario no está autenticado.");
        }
    }

    private static boolean validarCredenciales(String nombreUsuario, String contraseña) {
        // Las credenciales válidas son "admin" y "1234"
        if (nombreUsuario.equals("admin") && contraseña.equals("1234")) {
            return true;
        } else {
            return false;
        }
    }

    private static void mostrarMenuPrincipal() {
        // Mostrar el menú principal
        System.out.println("Menú principal");
        System.out.println("1. Dar de alta doctor");
        System.out.println("2. Dar de alta paciente");
        System.out.println("3. Crear cita");
        System.out.println("4. Mostrar cita");
        System.out.println("5. Salir");

        // Solicitar una opción al usuario
        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();

        // Realizar la acción correspondiente
        while (opcion != 5) {
            switch (opcion) {
                case 1:
                    darDeAltaDoctor();
                    break;
                case 2:
                    darDeAltaPaciente();
                    break;
                case 3:
                    crearCita();
                    break;
                case 4:
                    mostrarCitas();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }

            // Solicitar una nueva opción al usuario
            System.out.println("Menú principal");
            System.out.println("1. Dar de alta doctor");
            System.out.println("2. Dar de alta paciente");
            System.out.println("3. Crear cita");
            System.out.println("4. Mostrar cita");
            System.out.println("5. Salir");
            opcion = scanner.nextInt();
        }

        System.out.println("Hasta luego.");
    }

    public static void darDeAltaDoctor() {
        // Solicitar los datos del doctor
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el identificador del doctor: ");
        String identificador = scanner.nextLine();
        System.out.print("Ingrese el nombre completo del doctor: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Ingrese la especialidad del doctor: ");
        String especialidad = scanner.nextLine();

        // Crear un objeto de la clase Doctor
        Doctor doctor = new Doctor(identificador, nombreCompleto, especialidad);

        // Guardar los datos del doctor en un archivo CSV
        try (FileWriter fileWriter = new FileWriter(ARCHIVO_CSV_Doctor, true)) {
            fileWriter.write(doctor.getIdentificador() + "," + doctor.getNombreCompleto() + "," + doctor.getEspecialidad() + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos del doctor.");
        }

        System.out.println("El doctor ha sido dado de alta con éxito.");
    }
    public static void darDeAltaPaciente() {
        // Solicitar los datos del paciente
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del paciente: ");
        String ID = scanner.nextLine();
        System.out.print("Ingrese el nombre completo del paciente: ");
        String nombreCompleto = scanner.nextLine();

        // Crear un objeto de la clase Paciente
        Paciente paciente = new Paciente(ID, nombreCompleto);

        // Guardar los datos del paciente en un archivo CSV
        try (FileWriter fileWriter = new FileWriter(ARCHIVO_CSV_Paciente, true)) {
            fileWriter.write(paciente.getIdentificador() + "," + paciente.getNombreCompleto() + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos del paciente.");
        }

        System.out.println("El paciente ha sido dado de alta con éxito.");
    }

    public static void crearCita() {
        // Solicitar los datos de la cita
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el identificador de la cita: ");
        String identificador = scanner.nextLine();
        System.out.print("Ingrese la fecha de la cita (dd/MM/yyyy): ");
        String fecha = scanner.nextLine();
        System.out.print("Ingrese la hora de la cita (HH:mm): ");
        String hora = scanner.nextLine();
        System.out.print("Ingrese el motivo de la cita: ");
        String motivo = scanner.nextLine();

        // Solicitar el ID del paciente
        System.out.print("Ingrese el ID del paciente: ");
        String IDPaciente = scanner.nextLine();

        // Solicitar el identificador del doctor
        System.out.print("Ingrese el identificador del doctor: ");
        String identificadorDoctor = scanner.nextLine();

        // Crear una cita con los datos proporcionados
        Cita cita = new Cita(identificador, fecha, hora, motivo, IDPaciente, identificadorDoctor);

        // Guardar los datos de la cita en un archivo CSV
        try (FileWriter fileWriter = new FileWriter(ARCHIVO_CSV_Citas, true)) {
            fileWriter.write(cita.getIdentificador() + "," + cita.getFecha() + "," + cita.getHora() + "," + cita.getMotivo() + "," + IDPaciente + "," + identificadorDoctor + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos de la cita.");
        }

        System.out.println("La cita ha sido creada con éxito.");
    }
    public static void mostrarCitas() {
        // Abrir el archivo CSV
        try (FileReader fileReader = new FileReader(ARCHIVO_CSV_Citas)) {
            // Crear un lector de CSV
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Leer las citas del archivo
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                // Separar la línea en sus componentes
                String[] componentes = linea.split(",");

                // Crear una cita con los datos proporcionados
                Cita cita = new Cita(componentes[0], componentes[1], componentes[2], componentes[3], componentes[4], componentes[5]);
                // Mostrar la cita
                System.out.println(cita);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV.");
        }
    }
}
