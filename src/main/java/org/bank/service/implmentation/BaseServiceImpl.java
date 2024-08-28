package org.bank.service.implmentation;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bank.dto.BaseDto;
import org.bank.dto.FeatureInfoDto;
import org.bank.entity.BaseEntity;
import org.bank.exception.NotFoundException;
import org.bank.mapper.BaseMapper;
import org.bank.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
public abstract class BaseServiceImpl<ENTITY extends BaseEntity, DTO extends BaseDto> {

    private static final Logger logger = LogManager.getLogger(BaseServiceImpl.class);

    private final BaseRepository<ENTITY> repository;
    protected final BaseMapper<DTO, ENTITY> mapper;

    protected abstract FeatureInfoDto featureInfo();
    protected abstract void validate(DTO dto);

    public Page<DTO> findAll(final Pageable pageable) {
        logger.debug("Finding all {} with pageable: {}", featureInfo().getPlural(), pageable);
        Page<DTO> result = mapper.buildDtoPage(repository.findAll(pageable));
        logger.debug("Found {} entities", result.getTotalElements());
        return result;
    }

    public DTO findById(final String id) {
        logger.debug("Finding {} by ID: {}", featureInfo().getSingle(), id);
        ENTITY entity = repository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("%s with ID [%s] does not exist.", featureInfo().getSingle(), id);
                    logger.error(message);
                    return new NotFoundException(message);
                });
        DTO dto = mapper.toDto(entity);
        logger.debug("Found {}: {}", featureInfo().getSingle(), dto);
        return dto;
    }

    public DTO create(final DTO dto) {
        validate(dto);
        ENTITY entity = mapper.toEntity(dto);
        repository.save(entity);
        logger.info("Created {} with ID: {}", featureInfo().getSingle(), dto.getId());
        return dto;
    }

    public DTO updateById(final DTO dto, final String id) {
        validateIfEntityExists(id);
        validate(dto);

        dto.setId(id);
        ENTITY entity = mapper.toEntity(dto);
        repository.save(entity);
        logger.info("Updated {} with ID: {}", featureInfo().getSingle(), id);
        return dto;
    }

    public DTO deleteById(final String id) {
        DTO dto = this.findById(id);
        repository.deleteById(id);
        logger.info("Deleted {} with ID: {}", featureInfo().getSingle(), id);
        return dto;
    }

    public void validateIfEntityExists(final String id) {
        logger.debug("Validating existence of {} with ID: {}", featureInfo().getSingle(),id);
        if (!repository.existsById(id)) {
            String message = String.format("%s with ID [%s] does not exist.", featureInfo().getSingle(), id);
            logger.error(message);
            throw new NotFoundException(message);
        }
        logger.debug("{} with ID: {} exists", featureInfo().getSingle(), id);
    }
}