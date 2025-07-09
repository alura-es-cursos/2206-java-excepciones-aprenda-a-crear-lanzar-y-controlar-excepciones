public class PilaDeEjecucion {
    public static void metodo1() {
        System.out.println("[Inicio] - metodo1");

        metodo2();

        System.out.println("[Fin] - metodo1");
    }

    public static void metodo2() {
        System.out.println("[Inicio] - metodo2");

        Usuario usuario = null;
        System.out.println(usuario.nombre);
        System.out.println("[Fin] - metodo2");
    }

    public static void main(String[] args) {
        System.out.println("[Inicio] - main");

        try{
            metodo1();
        }catch (NullPointerException ex){
            System.out.println("Usuario no encontrado");
        }

        System.out.println("[Fin] - main");
    }
}

class Usuario {
    public String nombre;
    public Usuario(String nombre) {
        this.nombre = nombre;
    }
}