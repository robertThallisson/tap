package org.tap.ueg.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	  public Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
	    if (entityValue != null) {
	      return java.sql.Timestamp.valueOf(entityValue);
	    }
	    return null;
	  }

	  @Override
	  public LocalDateTime convertToEntityAttribute(java.sql.Timestamp databaseValue) {
	    if (databaseValue != null) {
	      return databaseValue.toLocalDateTime();
	    }
	    return null;
	  }

}