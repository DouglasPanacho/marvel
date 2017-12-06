
# Arquitetura Utilizada

A arquitetura utilizada foi MVP por proporcionar um código mais limpo e separado, onde cada classe possui sua própria função onde a view é responsável pela manipulação do UI, o presenter por realizar funções mais pesadas e complexas como requisições a servidores e o model para fazer a comunicação entre view e presenter.

# Bibliotecas

  Rxjava2/Rxandroid - Utilizado para possibilitar a programação funcional reativa, conseguindo dessa forma ter um melhor controle sobre eventos assíncronos com a ajuda de observables e observers.
  
Retrofit - Utilizado para ajudar na manipulação de dados através de requisições http, possibilitando que os resultados sejam convertidos em classes java a partir do Gson, melhorando o desenvolvimento e deixando o código mais limpo. O Retrofit foi utilizado em conjunto com as bibliotecas Rxjava2/Rxandroid.

OKHTTP - Utilizado para inserir interceptors nas requisições antes que essas fossem realizadas. No aplicativo foi utilizado para inserir queries necessárias para que as requests pudessem ser realizadas, como o public key, chave md5, e timestamp não sendo necessário passar sempre os mesmos parâmetros no código a cada nova request.

Dagger 2- Essa biblioteca foi utilizada com o intuito de usar a injeção de dependência, possibilitando dessa forma ter um código com menor acoplamento entre as classes, melhorar a manutenção e facilitar a criação de testes.

Room - Biblioteca utilizada para facilitar a manipulação do banco de dados local, possibilitando um código mais simples e com menos boilerplates, ficando melhor no momento da dar manutenção ou aplicar melhorias.

Butterknife - Biblioteca utilizada para facilitar o binding de views e eventos, deixando o desenvolvimento um pouco mais rápido.

Glide - Biblioteca utilizada para ajudar na manipulação de imagens recebidas através das requisições da api da Marvel. Escolhi usar o Glide ao invés do Picasso devido a alguns problemas passados em relação a orientação de imagens quando era utilizado o Picasso.
