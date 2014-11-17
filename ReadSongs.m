%%Data is downloaded from http://labrosa.ee.columbia.edu/millionsong/pages/getting-dataset#subset
%%The file name is "millionsongsubset_full.tar.gz"
%%Unzipping file: tar -zxvf millionsongsubset_full.tar.gz
%%Merge files from nested folders: cp -r MillionSongSubset/data/*/*/*/*.h5 path
%%There are total 10000 songs in the subset

list = dir('path'); %% Substitute 'path' by the real folder name which includes files
fileID = fopen('Songs', 'w');
for i = 3 : length(list) %% First 2 are '.' and '..'

    FileName = list(i).name;
    FileName = strcat('path/', FileName); %% Substitute 'path/' by the real folder name which includes files
    hinfo = hdf5info(FileName);
    dset = hdf5read(hinfo.GroupHierarchy.Groups(1).Datasets(11));
    
    x = [];
    for j = 1 : length(dset(1,:))
        pos = find((dset(:,j).*10) >= 10);
        x = [x, dec2hex(pos(1))];
    end
    fprintf(fileID, '%c', '<');
    fprintf(fileID, '%d', i);
    fprintf(fileID, '%c\n', '>');
    fprintf(fileID, '%s\n', x);
    
end
fclose(fileID);
fprintf('There are %i songs',i-2); %% i is from 3 to 2+number of songs
