# Comentarios:

![diagram logical](https://github.com/x1n4px/Trabajo-Grupo-SII/blob/main/PracticaGrupo/ER%20PEVAU.PNG?raw=true)
![diagrama relational](https://github.com/x1n4px/Trabajo-Grupo-SII/blob/main/PracticaGrupo/Relational_1.png?raw=true)


Las relaciones hacia ASISTENCIAS no están confirmadas, ya que hay que usar @Embeddable, aunque en el pdf de clase, hay otra opción que marca como preferida, que sería crear una nueva entidad y pasarla como variable con @EmbeddedId

Y ANE que es una entidad que hereda de estudiante, se ha creado como una clase normal, salvo por que en estudiante se ha añadido:
```
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
```

También faltaría ver si hay que añadir @Table al inicio de cada clase
