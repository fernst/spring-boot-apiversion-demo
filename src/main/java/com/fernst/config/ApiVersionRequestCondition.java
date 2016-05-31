package com.fernst.config;

import com.fernst.entity.ApiVersion;

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

  protected static final String GET = "get";
  protected static final String ACCEPT = "accept";
  protected static final String CONTENT_TYPE = "content-type";

  /**
   * The constant LOG.
   */
  protected static final org.slf4j.Logger LOG =
      org.slf4j.LoggerFactory.getLogger(ApiVersionRequestCondition.class);

  private final Set<Integer> versions;

  /**
   * Instantiates a new Api version request condition.
   *
   * @param version the version
   */
  public ApiVersionRequestCondition(Integer version) {
    this(Collections.singletonList(version));
  }

  /**
   * Instantiates a new Api version request condition.
   *
   * @param versions the versions
   */
  public ApiVersionRequestCondition(int[] versions) {
    this(IntStream.of(versions).boxed().collect(Collectors.toList()));
  }

  /**
   * Instantiates a new Api version request condition.
   *
   * @param versions the versions
   */
  public ApiVersionRequestCondition(Integer... versions) {
    this(Arrays.asList(versions));
  }

  /**
   * Instantiates a new Api version request condition.
   *
   * @param versions the versions
   */
  public ApiVersionRequestCondition(Collection<Integer> versions) {
    this.versions = Collections.unmodifiableSet(new HashSet<Integer>(versions));
  }

  @Override
  public ApiVersionRequestCondition combine(ApiVersionRequestCondition other) {
    //Combine the versions from both conditions.
    Set<Integer> allVersions = new LinkedHashSet<Integer>(this.versions);
    allVersions.addAll(other.versions);
    return new ApiVersionRequestCondition(allVersions);
  }

  @Override
  public ApiVersionRequestCondition getMatchingCondition(HttpServletRequest request) {
    try {
      String header;
      if (request.getMethod().equalsIgnoreCase(GET)) {
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
    return matchVersionToHeader(header, this.versions);
  }

  /**
   * Match version to header boolean.
   *
   * @param header   the header
   * @param versions the versions
   * @return the boolean
   */
  public static boolean matchVersionToHeader(String header, Set<Integer> versions) {
    //If it supports any version, return true.
    if (versions.contains(ApiVersion.ANY_VERSION)) {
      return true;
    }

    //match the ".vXX+" part of the header
    Matcher headerMatcher = Pattern.compile("\\.v[0-9]+\\+").matcher(header == null ? header : "");

    //If there's no version specified in the header, return false.
    if (!headerMatcher.find()) {
      return false;
    }

    //Extract the version number from the header
    int versionNumber = Integer.valueOf(headerMatcher.group(0).replaceAll("[^\\d]", ""));

    //Return true if the endpoints supports the specified version.
    //Otherwise, return false.
    return versions.contains(versionNumber);
  }
}
