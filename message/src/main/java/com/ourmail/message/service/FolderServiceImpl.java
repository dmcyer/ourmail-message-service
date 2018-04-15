package com.ourmail.message.service;

import com.ourmail.message.contract.Folder;
import com.ourmail.message.contract.FolderService;
import com.ourmail.message.domain.*;
import com.ourmail.message.repository.*;
import com.ourmail.user.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FolderServiceImpl implements FolderService {
    @Autowired
    private MailRepository mailRepository;
    @Autowired
    private MailReceiverRepository mailReceiverRepository;
    @Autowired
    private MailGroupReceiverRepository mailGroupReceiverRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private FolderService folderService;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private MailFolderRepository mailFolderRepository;

    @Override
    public long createUserInbox(long userId) {
        FolderDO folderDO = new FolderDO();
        folderDO.setName("UserInbox");
        folderDO.setType(1);
        folderDO.setParentId(userId);
        folderRepository.save(folderDO);
        return folderDO.getId();
    }

    @Override
    public long getUserInbox(long userId) {
        Iterable<FolderDO> FolderDOS= folderRepository.findAllByParentId(userId);
        for (FolderDO folderDO : FolderDOS) {

            if(folderDO.getType()>0) {

                if(folderDO.getName().equals("UserInbox")){
                return folderDO.getId();
            }}
        }
        return -1;
    }

    @Override
    public long createGroupInbox(long userId) {
        FolderDO folderDO = new FolderDO();
        folderDO.setName("GroupInbox");
        folderDO.setType(-1);
        folderDO.setParentId(userId);
        folderRepository.save(folderDO);
        return folderDO.getId();
    }

    @Override
    public long getGroupInbox(long userId) {
        Iterable<FolderDO> FolderDOS= folderRepository.findAllByParentId(userId);
        for (FolderDO folderDO : FolderDOS) {
            if(folderDO.getType()<0) {
            if(folderDO.getName().equals("GroupInbox")){
                return folderDO.getId();
            }}
        }
        return 0;
    }

    @Override
    public long createUserOutbox(long userId) {
        FolderDO folderDO = new FolderDO();
        folderDO.setName("UserOutbox");
        folderDO.setType(2);
        folderDO.setParentId(userId);
        folderRepository.save(folderDO);
        return folderDO.getId();
    }

    @Override
    public long getUserOutbox(long userId) {
        Iterable<FolderDO> FolderDOS= folderRepository.findAllByParentId(userId);
        for (FolderDO folderDO : FolderDOS) {
            if(folderDO.getType()>0) {

            if(folderDO.getName().equals("UserOutbox")){
                return folderDO.getId();
            }
            }
        }
        return 0;
    }

    @Override
    public long createUserFolder(long userId, String name, long parentId) {
        FolderDO folderDO = new FolderDO();
        folderDO.setName(name);
        folderDO.setType(4);
        folderDO.setParentId(parentId);
        folderRepository.save(folderDO);
        return folderDO.getId();
    }

    @Override
    public long createGroupFolder(long userId, String name, long parentId) {
        FolderDO folderDO = new FolderDO();
        folderDO.setName(name);
        folderDO.setType(-4);
        folderDO.setParentId(parentId);
        folderRepository.save(folderDO);
        return folderDO.getId();
    }

    @Override
    public List<Folder> getUserFolderList(long userId) {
        Iterable<FolderDO> FolderDOS= folderRepository.findAllByParentId(userId);
        List<Folder> FolderDOret = new ArrayList<>();
        for (FolderDO folderDO : FolderDOS) {
            if(folderDO.getType()>0) {
                Folder folder = new Folder();//加入后能否改变后再次加入
                folder.setName(folderDO.getName());
                folder.setparentId(folderDO.getParentId());
                folder.setType(folderDO.getType());
                FolderDOret.add(folder);
            }
        }
        return FolderDOret;
    }

    @Override
    public List<Folder> getGroupFolderList(long userId) {
        Iterable<FolderDO> FolderDOS= folderRepository.findAllByParentId(userId);
        List<Folder> FolderDOret = new ArrayList<>();
        for (FolderDO folderDO : FolderDOS) {
            if(folderDO.getType()<0) {
                Folder folder = new Folder();//加入后能否改变后再次加入
                folder.setName(folderDO.getName());
                folder.setparentId(folderDO.getParentId());
                folder.setType(folderDO.getType());
                FolderDOret.add(folder);
            }
        }
        return FolderDOret;
    }
}
