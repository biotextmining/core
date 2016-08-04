ALTER TABLE `corpus_has_publications_has_processes` 
DROP FOREIGN KEY `fk_corpus_has_publications_has_processes_processes1`;

ALTER TABLE `corpus_has_publications_has_processes` 
ADD CONSTRAINT `fk_corpus_has_publications_has_processes_processes1`
  FOREIGN KEY (`chphp_processes_id`)
  REFERENCES `processes` (`pro_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
