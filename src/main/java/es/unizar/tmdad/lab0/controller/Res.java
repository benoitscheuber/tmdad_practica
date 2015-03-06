package es.unizar.tmdad.lab0.controller;

import org.springframework.social.twitter.api.SearchResults;
import com.fasterxml.jackson.annotation.JsonView;

public class Res {

    private SearchResults resultat;

    public Res() {
    }

    public Res(SearchResults searchResults) {
        this.resultat = searchResults;
    }

    @JsonView
    public SearchResults getRes() {
        return this.resultat;
    }
}