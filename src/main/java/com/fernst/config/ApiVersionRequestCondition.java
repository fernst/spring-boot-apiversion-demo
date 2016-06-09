package com.fernst.config;

import com.fernst.entity.ApiVersion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Api version request condition.
 */
public class ApiVersionRequestCondition implements RequestCondition<ApiVersionRequestCondition> {

    /**
     * The constant GET.
     */
    protected static final String GET = "get";
    /**
     * The constant ACCEPT.
     */
    protected static final String ACCEPT = "accept";
    /**
     * The constant CONTENT_TYPE.
     */
    protected static final String CONTENT_TYPE = "content-type";

    /**
     * The constant LOG.
     */
    protected static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ApiVersionRequestCondition.class);

    private final Set<Integer> versions;
    private final Integer min;
    private final Integer max;
    private final Integer baseVersion;
    private final Integer latestVersion;

    /**
     * Instantiates a new Api version request condition.
     *
     * @param versions      the versions
     * @param min           the min
     * @param max           the max
     * @param baseVersion   the base version
     * @param latestVersion the latest version
     */
    public ApiVersionRequestCondition(int[] versions, int min, int max, int baseVersion, int latestVersion) {
        this(IntStream.of(versions)
                .boxed()
                .collect(Collectors.toList()), min, max, baseVersion, latestVersion);
    }

    /**
     * Instantiates a new Api version request condition.
     *
     * @param versions      the versions
     * @param min           the min
     * @param max           the max
     * @param baseVersion   the base version
     * @param latestVersion the latest version
     */
    public ApiVersionRequestCondition(Collection<Integer> versions, Integer min, Integer max, Integer baseVersion,
                                      Integer latestVersion) {
        this.versions = Collections.unmodifiableSet(new HashSet<Integer>(versions));
        this.min = min;
        this.max = max;
        this.baseVersion = baseVersion;
        this.latestVersion = latestVersion;
    }

    @Override
    public ApiVersionRequestCondition combine(ApiVersionRequestCondition other) {
        //Combine the versions from both conditions.
        Set<Integer> allVersions = new LinkedHashSet<Integer>(this.versions);
        allVersions.addAll(other.versions);
        return new ApiVersionRequestCondition(allVersions, Math.min(this.min, other.min), Math.max(this.max, other
                .max), this.baseVersion, this.latestVersion);
    }

    @Override
    public ApiVersionRequestCondition getMatchingCondition(HttpServletRequest request) {
        try {
            String header;
            if (request.getMethod()
                    .equalsIgnoreCase(GET)) {
                header = request.getHeader(ACCEPT);
            } else {
                header = request.getHeader(CONTENT_TYPE);
            }

            if (!matchVersionToHeader(header)) {
                return null;
            }

            return this;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int compareTo(ApiVersionRequestCondition other, HttpServletRequest request) {
        Set<Integer> versionComparison = new LinkedHashSet<>(this.versions);

        versionComparison.removeAll(other.versions);

        return versionComparison.size();
    }

    /**
     * Match version to header boolean.
     *
     * @param header the header
     * @return the boolean
     */
    public boolean matchVersionToHeader(String header) {
        //If the annotation it's in default initialization, return true.
        if (versions.contains(ApiVersion.ANY_VERSION) && min == 0 && max == Integer.MAX_VALUE) {
            return true;
        }

        Integer version = getVersionNumber(header);

        //Return true if the endpoints supports the specified version.
        //Otherwise, return false.
        return (version != null && (versions.contains(version) || versions.contains(ApiVersion.ANY_VERSION))) &&
                (min == null || (version >= min && version >= baseVersion)) &&
                (max == null || (version <= max && version <= latestVersion));
    }

    /**
     * Gets version number.
     *
     * @param header the header
     * @return the version number
     */
    public static Integer getVersionNumber(String header) {
        //match the ".vXX+" part of the header
        //You can add extra rules to filter specific vendor names (such as vnd.fernst)
        Matcher headerMatcher = Pattern.compile("\\.v[0-9]+\\+")
                .matcher(header != null ? header : "");

        //If there's no version specified in the header, return null.
        if (!headerMatcher.find()) {
            return null;
        }

        //Extract the version number from the header
        return Integer.valueOf(headerMatcher.group(0)
                .replaceAll("[^\\d]", ""));
    }
}
