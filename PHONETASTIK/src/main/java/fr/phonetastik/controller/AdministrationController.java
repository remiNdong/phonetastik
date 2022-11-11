package fr.phonetastik.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.phonetastik.model.Marque;
import fr.phonetastik.model.Nomdefichierimage;
import fr.phonetastik.service.MarqueService;
import fr.phonetastik.service.NomdefichierimageService;
import fr.phonetastik.utils.StorageException;
import fr.phonetastik.utils.StorageService;

@Controller
public class AdministrationController {

	@Autowired
	StorageService storageService;

	@Autowired
	NomdefichierimageService nomdefichierimageService;

	@Autowired
	MarqueService marqueService;

	@GetMapping(value = "/administration")
	public String viewTemplate(Model model) {
		return "administration";
	}

	@GetMapping(value = "/administration/creationMarque")
	public String viewTemplateCreation(@Valid Marque marque) {
		return "creationMarque";
	}

	@GetMapping(value = "/administration/modificationMarque")
	public String viewTemplateModification() {
		return "redirect:/listeMarques";
	}

	@GetMapping(value = "/administration/modificationMarque/{id}")
	public String viewTemplateModificationNumero(Model model, @PathVariable long id) {

		Marque marque = marqueService.findMarqueById(id);
		model.addAttribute("marque", marque);
		return "modificationMarque";
	}

	@PostMapping(value = "/administration/modificationMarque/{id}")
	public String viewTemplateModificationNumeroPost(Model model, @PathVariable long id, HttpServletRequest request) {

		Marque marque = marqueService.findMarqueById(id);
		model.addAttribute("marque", marque);

		String visible = request.getParameter("visible");
		marque.setVisible(visible);

		marqueService.enregistrer(marque);
		return "redirect:/listeMarques";
	}

	@GetMapping(value = "listeMarques")
	public String viewTemplateListeMarques(Model model) {

		// model.addAttribute("map", storageService.getMap());

		try {

			model.addAttribute("listeMarques", marqueService.lister());
			return "listeMarques";

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}

	}

	@PostMapping(value = "/administration/creationMarque")
	public String viewTemplateCreationPost(@RequestParam("imageMarque") MultipartFile file, @Valid Marque marque,
			Model model) {

		try {

			/*
			 * if
			 * (nomdefichierimageService.findNomDeFichierImageById(file.getOriginalFilename(
			 * )) != null) { throw new
			 * StorageException("Il y a deja une image de ce nom, changer le nom de l'image"
			 * ); }
			 */

			if (nomdefichierimageService.findNomDeFichierImageById(marque.getNom() + ".jpg") != null) {
				throw new StorageException("Il y a deja une image de ce nom, changer le nom de l'image");
			}

			if (marqueService.findMarqueByNom(marque.getNom()).isEmpty()) {

				storageService.store(file, marque.getNom() + ".jpg");

				Nomdefichierimage nomdefichierimage = new Nomdefichierimage(marque.getNom() + ".jpg");
				nomdefichierimageService.enregistrer(nomdefichierimage);

				marque.setFilename(marque.getNom() + ".jpg");
				marqueService.enregistrer(marque);
				return "redirect:/listeMarques";

			} else {
				model.addAttribute("erreurCreationMarque",
						"Il ne peut y avoir qu'une marque de  nom " + marque.getNom());
				return "redirect:/creationMarque";
			}

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}

	}

}
