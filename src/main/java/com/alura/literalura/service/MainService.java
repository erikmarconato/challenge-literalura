package com.alura.literalura.service;

import com.alura.literalura.dto.BookDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;

@Service
public class MainService {

    private final BookService bookService;
    private final AuthorService authorService;

    public MainService(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public void MenuOptions () throws IOException, InterruptedException {

        boolean actived = true;

        Scanner scanner = new Scanner(System.in);
        Scanner scannerYear = new Scanner(System.in);

        while (actived){

            System.out.println("******************************************************" +
                    "\n" +
                    "Escolha o número de sua opção: " +
                    "\n" +
                    "\n" +
                    "1) Buscar livro pelo título" + "\n" +
                    "2) Listar livros registrados" + "\n" +
                    "3) Listar autores registrados" + "\n" +
                    "4) Listar autores vivos em um determinado ano" + "\n" +
                    "5) Listar livros em um determinado idioma" + "\n" +
                    "0) Sair" + "\n" +
                    "******************************************************"
            );

            int option = scanner.nextInt();

            switch (option){
                case 1:
                    scanner.nextLine();
                    System.out.println("Insira o nome do livro que você deseja procurar");
                    String typedBookName = scanner.nextLine();

                    BookDto returnedBook = bookService.saveData(typedBookName);

                    if (returnedBook == null) {
                        System.out.println("Livro não encontrado!");
                        break;
                    }

                    System.out.println(
                            "----- LIVRO -----" + "\n" +
                            "Título: " + returnedBook.title() + "\n" +
                            "Autor: " + returnedBook.authors().get(0).name() + "\n" +
                            "Idioma: " + returnedBook.languages() + "\n" +
                            "Número de Downloads: " + returnedBook.downloadCount()
                    );
                    break;

                case 2:
                    bookService.listAllBooks().forEach(l -> {
                        System.out.println("Título   : " + l.title());
                        System.out.println("Autor    : " + l.author());
                        System.out.println("Idioma   : " + l.language());
                        System.out.println("Downloads: " + l.downloadNumber());
                        System.out.println("---------------------------");
                    });
                    break;

                case 3:
                    authorService.listAllAuthors().forEach(a -> {
                        System.out.println("Autor: " + a.name());
                        System.out.println("Ano de Nascimento: " + a.birthYear());
                        System.out.println("Ano de Falecimento: " + a.deathYear());

                        String livros = String.join(", ", a.book());
                        System.out.println("Livros: " + livros);
                        System.out.println("---------------------------");
                    });
                    break;

                case 4:
                    System.out.println("Insira o ano que deseja pesquisar");
                    Integer yearEntered = scannerYear.nextInt();
                    authorService.listLivingAuthors(yearEntered).forEach(a -> {
                                System.out.println("Autor: " + a.name());
                                System.out.println("Ano de Nascimento: " + a.birthYear());
                                System.out.println("Ano de Falecimento: " + a.deathYear());

                                String livros = String.join(", ", a.book());
                                System.out.println("Livros: " + livros);
                                System.out.println("---------------------------");
                            });
                    break;

                case 5:
                    scanner.nextLine();
                    System.out.println("Insira o idioma para realizar a busca: ");
                    System.out.println("es - espanhol");
                    System.out.println("en - inglês");
                    System.out.println("fr - francês");
                    System.out.println("pt - português");

                    String languageEntered = scanner.nextLine();

                    switch (languageEntered) {
                        case "es", "en", "fr", "pt" -> {
                            var livros = bookService.listAllBooksWithGivenLanguage(languageEntered);

                            if (livros.isEmpty()) {
                                System.out.println("Não existem livros nesse idioma no banco de dados.");
                            } else {
                                livros.forEach(l -> {
                                    System.out.println("Título   : " + l.title());
                                    System.out.println("Autor    : " + l.author());
                                    System.out.println("Idioma   : " + l.language());
                                    System.out.println("Downloads: " + l.downloadNumber());
                                    System.out.println("---------------------------");
                                });
                            }
                        }
                    }
                    break;

                case 0:
                    System.out.println("Você saiu.");
                    actived = false;
                    break;

                default:
                    System.out.println("Você escolheu uma opção inválida. Tente Novamente!");
                    break;

            }
        }
        scannerYear.close();
        scanner.close();
    }
}
