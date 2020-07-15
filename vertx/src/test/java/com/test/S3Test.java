package com.test;

import com.hubrick.vertx.s3.client.S3Client;
import com.hubrick.vertx.s3.client.S3ClientOptions;
import com.hubrick.vertx.s3.model.request.GetObjectRequest;
import com.hubrick.vertx.s3.model.request.InitMultipartUploadRequest;
import io.vertx.core.Vertx;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-04-13 14:49
 * @Version 1.0
 * @Description MainTest
 */
public class S3Test {


    public static void main(String[] args) {
        final S3ClientOptions clientOptions = new S3ClientOptions()
                .setAwsRegion("localhost")
                .setHostnameOverride("10.129.40.2:9000")
                .setAwsServiceName("s3")
                .setAwsAccessKey("9z3e2PjovUT2ts54")
                .setAwsSecretKey("g8knLrURFttmbuI1VsJG43x3yCKveW");
        //.setSignPayload(true);

        Vertx vertx = Vertx.vertx();
        final S3Client s3Client = new S3Client(vertx, clientOptions);

        s3Client.getObject(
                "whxjg01",
                "test.txt",
                new GetObjectRequest().withResponseContentType("application/json"),
                response -> {
                    System.out.println("Response from AWS: " + response.getHeader().getContentType());
                },
                Throwable::printStackTrace
        );

//        s3Client.getObjectAcl(
//                "whxjg01",
//                "test.txt",
//                response -> System.out.println("Response from AWS: " + response.getData().getOwner()),
//                Throwable::printStackTrace
//        );
//
//        s3Client.putObject(
//                "whxjg01",
//                "test.txt",
////                new PutObjectRequest(Buffer.buffer("test")).withContentType("application/json"),
//                new PutObjectRequest(Buffer.buffer("test")),
//                response -> System.out.println("Response from AWS: " + response.getHeader().getContentType()),
//                Throwable::printStackTrace
//        );
//
//        s3Client.putObjectAcl(
//                "whxjg01",
//                "test.txt",
//                new PutObjectAclRequest(new AclHeadersRequest().withAmzAcl(CannedAcl.PUBLIC_READ_WRITE)),
//                response -> System.out.println("Response from AWS: " + response.getHeader().getContentType()),
//                Throwable::printStackTrace
//        );
//
//        s3Client.deleteObject(
//                "bucket",
//                "key",
//                new DeleteObjectRequest(),
//                response -> System.out.println("Response from AWS: " + response.getHeader().getContentType()),
//                Throwable::printStackTrace
//        );
//
//        s3Client.copyObject(
//                "sourceBucket",
//                "sourceKey",
//                "destinationBucket",
//                "destinationKey",
//                new CopyObjectRequest(),
//                response -> System.out.println("Response from AWS: " + response.getHeader().getContentType()),
//                Throwable::printStackTrace
//        );
//
//        s3Client.headObject(
//                "bucket",
//                "key",
//                new HeadObjectRequest(),
//                response -> System.out.println("Response from AWS: " + response.getHeader().getContentType()),
//                Throwable::printStackTrace
//        );
//
//        // List bucket v2
//        s3Client.getBucket(
//                "bucket",
//                new GetBucketRequest().withPrefix("prefix"),
//                response -> System.out.println("Response from AWS: " + response.getData().getName()),
//                Throwable::printStackTrace
//        );

//        s3Client.initMultipartUpload(
//                "whxjg01",
//                "ccc.txt",
//                new InitMultipartUploadRequest(),
//                response -> System.out.println("Response from AWS: " + response.getData().getInitMultipartUploadResponse()),
//               x->{
//                   System.out.println(x);
//               }
//        );


    }

}
