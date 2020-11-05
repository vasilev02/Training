package entities.footballBookmakerSystem;

import entities.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    private String name;
    private Blob logo;
    private String initials;
    private Color primary_kit_color;
    private Color secondary_kit_color;
    private Town town;
    private BigDecimal budget;
    private Set<Player> players;

    public Team() {
    }

    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    @Column(name = "logo",nullable = false)
    public Blob getLogo() {
        return logo;
    }

    public void setLogo(Blob logo) {
        this.logo = logo;
    }

    @Column(name = "initials",nullable = false,length = 3)
    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @ManyToOne
    @JoinColumn(name = "primary_color_id",referencedColumnName = "id")
    public Color getPrimary_kit_color() {
        return primary_kit_color;
    }

    public void setPrimary_kit_color(Color primary_kit_color) {
        this.primary_kit_color = primary_kit_color;
    }

    @ManyToOne
    @JoinColumn(name = "secondary_color_id",referencedColumnName = "id")
    public Color getSecondary_kit_color() {
        return secondary_kit_color;
    }

    public void setSecondary_kit_color(Color secondary_kit_color) {
        this.secondary_kit_color = secondary_kit_color;
    }

    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Column(name = "budget",nullable = false)
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @OneToMany(mappedBy = "team", targetEntity = Player.class)
    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
