package com.mertao.tennisscoreboard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matches")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Match {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "player1", referencedColumnName = "id", nullable = false)
	private Player player1;
	
	@ManyToOne
	@JoinColumn(name = "player2", referencedColumnName = "id", nullable = false)
	private Player player2;
	
	@ManyToOne
	@JoinColumn(name = "winner", referencedColumnName = "id", nullable = false)
	private Player winner;
}
