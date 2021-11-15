package br.ufma.lsdi.digitalphenotyping.phenotypecomposer.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Phenotypes.class}, version = 1)
public abstract class AppDatabasePC extends RoomDatabase {
    public abstract PhenotypeDAO phenotypeDAO();
}