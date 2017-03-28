package es.uniovi.asw.model;

public class Association {

	public static class Sugerir {

		public static void link(Citizen citizen, Sugerencia sugerencia, Categoria categoria) {
			sugerencia._setCategoria(categoria);;
			sugerencia._setCitizen(citizen);;
			
			citizen._getSugerencias().add(sugerencia);
			categoria._getSugerencias().add(sugerencia);
			
		}
		
		public static void unlink(Citizen citizen, Sugerencia sugerencia, Categoria categoria) {
			citizen._getSugerencias().remove(sugerencia);
			categoria._getSugerencias().remove(sugerencia);
			
			sugerencia._setCategoria(null);;
			sugerencia._setCitizen(null);;
			
		}

	}
	
	public static class Comentar {

		public static void link(Citizen citizen, Comentario comentario, Sugerencia sugerencia) {
			comentario._setCitizen(citizen);
			comentario._setSugerencia(sugerencia);
			
			citizen._getComentarios().add(comentario);
			sugerencia._getComentarios().add(comentario);
			
		}

		public static void unlink(Citizen citizen, Comentario comentario, Sugerencia sugerencia) {
			citizen._getComentarios().remove(comentario);
			sugerencia._getComentarios().remove(comentario);
			
			comentario._setCitizen(null);
			comentario._setSugerencia(null);			
		}
	}
	
	public static class VotarComentario {

		public static void link(Comentario comentario, VotoComentario voto, Citizen citizen) {
			voto._setComentario(comentario);
			voto._setCitizen(citizen);
			
			comentario._getVotos().add(voto);
			citizen._getVotosComentarios().add(voto);
			
		}

		public static void unlink(Comentario comentario, VotoComentario voto, Citizen citizen) {
			comentario._getVotos().remove(voto);
			citizen._getVotosComentarios().remove(voto);
			
			voto._setComentario(null);
			voto._setCitizen(null);			
		}

	}
	
	public static class VotarSugerencia {

		public static void link(Sugerencia sugerencia, VotoSugerencia voto, Citizen citizen) {
			voto._setSugerencia(sugerencia);
			voto._setCitizen(citizen);
			
			sugerencia._getVotos().add(voto);
			citizen._getVotosSugerencias().add(voto);
			
		}
		
		public static void unlink(Sugerencia sugerencia, VotoSugerencia voto, Citizen citizen) {
			sugerencia._getVotos().remove(voto);
			citizen._getVotosSugerencias().remove(voto);
			
			voto._setSugerencia(null);
			voto._setCitizen(null);
		}

	}
	

}
