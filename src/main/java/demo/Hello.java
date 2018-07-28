package demo;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.github.freewind.lostlist.Lists.list;

public class Hello {

    public static void main(String[] args) throws IOException {
        Page page = new Page(
                list("aaa@test.com", "bbb@test.com"),
                list(
                        new Item("Item 1", "$19.99", list(new Feature("New!"), new Feature("Awesome!"))),
                        new Item("Item 2", "$29.99", list(new Feature("Old."), new Feature("Ugly.")))
                ));
        MustacheFactory factory = new DefaultMustacheFactory("templates");

        Mustache mustache = factory.compile(factory.getReader("hello.html"), "hello");
        mustache.execute(new PrintWriter(System.out), page).flush();
    }

}

class Page {
    final List<String> emails;
    final List<Item> items;
    public Page(List<String> emails, List<Item> items) {
        this.emails = emails;
        this.items = items;
    }
}

class Feature {
    final String description;
    public Feature(String description) {
        this.description = description;
    }
}

class Item {
    final String name;
    final String price;
    final List<Feature> features;
    public Item(String name, String price, List<Feature> features) {
        this.name = name;
        this.price = price;
        this.features = features;
    }
}
