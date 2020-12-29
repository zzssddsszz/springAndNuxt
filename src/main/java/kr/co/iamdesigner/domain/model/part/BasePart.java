package kr.co.iamdesigner.domain.model.part;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BASE_PART")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BasePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BASE_PART_ID")
    private Long id;

    @OneToMany(mappedBy = "basePart")
    private List<SuppliedPart> suppliedPart = new ArrayList<>();

    private String name;

    private int buyPrice;

    private int sellPrice;

    private int stock;

    @Enumerated(EnumType.STRING)
    private PlatingColor color;

    @Enumerated(EnumType.STRING)
    private Material material;

    @Builder
    public BasePart(String name, int buyPrice, int sellPrice, int stock, PartType partType, PlatingColor color, Material material) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.stock = stock;
        this.color = color;
        this.material = material;
    }
}
