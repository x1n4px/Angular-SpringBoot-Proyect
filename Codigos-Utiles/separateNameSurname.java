public class separateNameSurname {
    public static void main(String[] args) {
       String entrada = "juan de la torre la calle";
       String[] partes = entrada.split(" ");

       String nombre = partes[0];
       String primerApellido = "";
       String segundoApellido = "";

       for (int i=1; i< partes.length; i++){
           if(partes[i].length() <=3){
               primerApellido += partes[i] + " ";
           }else{
               primerApellido += partes[i];
               break;
           }
       }
       for (int i=primerApellido.split(" ").length +1; i < partes.length; i++){
           segundoApellido += partes[i] + " ";
       }


        System.out.println(nombre);
        System.out.println(primerApellido);
        System.out.println(segundoApellido.trim());


    }
}